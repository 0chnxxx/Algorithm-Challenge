import os
import requests

# GitHub API 관련 설정
GITHUB_TOKEN = os.getenv('GITHUB_TOKEN')
OWNER = os.getenv('OWNER')
REPOSITORY = os.getenv('REPOSITORY')

# Github API 설정
HOST = f"https://api.github.com/repos/{OWNER}/{REPOSITORY}"

headers = {
    "Authorization": f"Bearer {GITHUB_TOKEN}",
    "Accept": "application/vnd.github+json",
    "X-Github-Api-Version": "2022-11-28"
}

def fetch_contributors():
    response = requests.get(f"{HOST}/stats/contributors", headers=headers)
    response_json = response.json()

    return [
        {
            "total": data["total"],
            "login": data["author"]["login"],
            "avatar_url": data["author"]["avatar_url"],
            "html_url": data["author"]["html_url"]
        }
        for data in response_json
    ]


def update_readme(contributors):
    readme_path = "../README.md"

    with open(readme_path, "r", encoding="utf-8") as file:
        readme_content = file.readlines()

    content = "### 기여도 TOP 5"
    content += "<table>\n"

    for contributor in contributors:
        content += (
            "<tr>\n"
            "<td align='center'>\n"
            f"<img src='{contributor['avatar_url']}' width='100' height='100'><br>\n"
            f"<a href='{contributor['html_url']}'>{contributor['login']}</a><br>\n"
            f"<span>{contributor['total']} commits</span>\n"
            "</td>\n"
            "</tr>\n"
        )

    content += "</table>"

    start_tag = "<!-- BEGIN TOP CONTRIBUTORS -->"
    end_tag = "<!-- END TOP CONTRIBUTORS -->"

    start_index = readme_content.index(start_tag) + 1
    end_index = readme_content.index(end_tag)

    updated_content = (
            readme_content[:start_index] +
            [content] +
            readme_content[end_index:]
    )

    with open(readme_path, "w", encoding="utf-8") as file:
        file.writelines(updated_content)


def main():
    total_contributors = fetch_contributors()
    best_contributors = sorted(total_contributors, key=lambda x: x["total"], reverse=True)[:10]

    update_readme(best_contributors)


if __name__ == "__main__":
    main()
