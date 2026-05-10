module.exports = async ({github, context, core}) => {
    const {owner, repo} = context.repo;
    core.info(`Running script in ${owner}/${repo}`);

    const releaseTag = context.payload.release.tag_name;
    const releaseLoader = releaseTag.substring(releaseTag.lastIndexOf('-' + 1).toLowerCase());
    const releaseUrl = context.payload.release.html_url;

    // Fetch all previous releases
    const releases = await github.paginate(
            github.rest.repos.listReleases,
            {
                owner,
                repo,
                per_page: 100
            }
    );

    // Find the most recent release for the same loader
    const previousRelease = releases.find(r => {
        if (r.tag_name === releaseTag)
            return false;
        const tag = r.tag_name;
        const loader = tag.substring(tag.lastIndexOf('-' + 1).toLowerCase());
        return loader === releaseLoader;
    })

    // Fetch all commits between the two releases
    const commits = await github.rest.repos.compareCommits({
        owner,
        repo,
        base: previousRelease.tag_name,
        head: releaseTag,
    });

    // Fetch all PRs associated with those commits
    const prNumbers = new Set();
    for (const commit of commits.data.commits) {
        const pulls = await github.rest.repos.listPullRequestsAssociatedWithCommit({
            owner,
            repo,
            commit_sha: commit.sha,
        });
        pulls.data.forEach(pr => prNumbers.add(pr.number));
    }

    // Comment on each PR
    for (const prNumber of prNumbers) {
        await github.rest.issues.createComment({
            owner,
            repo,
            issue_number: prNumber,
            body: `This PR has been included in release: [${releaseTag}](${releaseUrl})`
        });
        console.log(`Commented on PR #${prNumber}`);
    }
}
