"""Writes a Markdown test summary from Surefire XML reports into the GitHub Actions job summary."""

import glob
import os
import xml.etree.ElementTree as ET

rows, passed, failed, skipped = [], 0, 0, 0
for report in sorted(glob.glob("target/surefire-reports/TEST-*.xml")):
    for case in ET.parse(report).getroot().iter("testcase"):
        if case.find("failure") is not None or case.find("error") is not None:
            status, failed = "❌ failed", failed + 1
        elif case.find("skipped") is not None:
            status, skipped = "⏭️ skipped", skipped + 1
        else:
            status, passed = "✅ passed", passed + 1
        test_class = case.get("classname", "").rsplit(".", 1)[-1]
        rows.append(f"| {test_class} | `{case.get('name')}` | {status} | {float(case.get('time', 0)):.1f}s |")

lines = [
    "## Android test results",
    "",
    f"**{passed} passed, {failed} failed, {skipped} skipped** on an Android emulator via Appium UiAutomator2",
    "",
    "| Class | Test | Result | Time |",
    "| --- | --- | --- | ---: |",
    *rows,
]
markdown = "\n".join(lines) + "\n"
if os.getenv("GITHUB_STEP_SUMMARY"):
    with open(os.environ["GITHUB_STEP_SUMMARY"], "a") as summary:
        summary.write(markdown)
print(markdown)
