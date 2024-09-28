#!/usr/bin/env python

import re
import sys

regex = r"problems/(.*?)/"
file_template = """package leetcode

class {pascal_case_name} {{

}}
"""


def kebab_to_pascal(kebab_str: str) -> str:
    return "".join(word.capitalize() for word in kebab_str.split("-"))


def main():
    url = sys.argv[1]
    match = re.search(regex, url)
    if match is None:
        print(f"Could not parse url: {url}", file=sys.stderr)
        exit(1)
    kebab_case_name = match.group(1)
    pascal_case_name = kebab_to_pascal(kebab_case_name)
    file_path = f"src/main/kotlin/leetcode/{pascal_case_name}.kt"

    with open(file_path, "w") as f:
        f.write(file_template.format(pascal_case_name=pascal_case_name))
        print(f"Created file {file_path}")


if __name__ == "__main__":
    main()
