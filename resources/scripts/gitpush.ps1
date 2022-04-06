Write-Host 'Pushing failed tests xml to sprint'
Copy-item -path C:\Users\Administrator\.jenkins\workspace\Zurple_AutomationFramework_Staging\failedTestFormatted.xml -destination C:\FailedTests\z57_selenium\src\test\resources\WorkFlows\zurple -Force
cd C:\FailedTests\z57_selenium
Write-Host 'Current branch name'
git branch 
Write-Host 'git status'
git status
write-Host 'pulling..'
git pull origin sprint
Write-Host 'adding..'
git add src/test/resources/WorkFlows/zurple/failedTestFormatted.xml
Write-Host 'commiting..'
git commit -m "pushing failed tests xml to sprint"
Write-Host 'pushing..'
git push origin sprint