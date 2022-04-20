cd C:\FailedTests\z57_selenium
Write-Host 'Checkout sprint'
git checkout sprint
Write-Host 'Current branch name'
git branch 
Write-Host 'git status'
git status
write-Host 'pulling..'
git pull origin sprint
Write-Host 'Deleting old failed tests xml'
del C:\FailedTests\z57_selenium\src\test\resources\WorkFlows\zurple\failedTestFormatted.xml
Write-Host 'Adding deleted failedTestFormatted.xml'
git add src/test/resources/WorkFlows/zurple/failedTestFormatted.xml
Write-Host 'Commiting deleted failedTestFormatted.xml'
git commit -m "Commiting deleted failed tests xml"
Write-Host 'Pushing deleted failedTestFormatted.xml'
git push origin sprint
Write-Host 'Copying failed tests xml to sprint'
Copy-item -path C:\Users\Administrator\.jenkins\workspace\Zurple_AutomationFramework_Staging\failedTestFormatted.xml -destination C:\FailedTests\z57_selenium\src\test\resources\WorkFlows\zurple -Force
Write-Host 'adding..'
git add src/test/resources/WorkFlows/zurple/failedTestFormatted.xml
Write-Host 'commiting..'
git commit -m "pushing failed tests xml to sprint"
Write-Host 'pushing..'
git push origin sprint