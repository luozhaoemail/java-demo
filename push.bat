echo "1 Start submitting code to the local repository"
echo "2 The current directory is：%cd%"
git add *
echo;
 
echo "3 Commit the changes to the local repository"
set now=%date% %time%
echo %now%
git commit -m "%now%"
echo;
 
echo "4 Commit the changes to the remote git server"
git push origin master 
echo;
 
echo "5 Batch execution complete!"
