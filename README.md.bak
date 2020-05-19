# java-demo
常用的java代码示例
  
========================  
Git 为你的每一个提交都记录你的名字与电子邮箱地址  
git config --global user.name 'Your Name'  
git config --global user.email you@somedomain.com  
  
==========================1  
git clone https://github.com/luozhaoemail/java-demo.git  
git clone git@github.com:luozhaoemail/java-demo.git  

==========================2  
git add <file> # 将工作文件修改提交到本地暂存区  
git add hello.java  
git add . 或者git add *    # 将所有修改过的工作文件提交暂存区  
  
==========================3  
将git commit的默认编辑器从nano转为vim  
-----nano垃圾不好用 Ctrl + X然后输入y再然后回车，就可以退出了  
git config --global core.editor vim  
git commit 			这会自动打开编辑器  
git commit -a  		自动将在提交前将已记录、修改的文件放入缓存区  
git commit -m '注释内容'   	# -m 选项以在命令行中提供提交注释。  
  
==========================4  
git push origin master # 将本地主分支推到远程主分支  
git push -u origin master # 将本地主分支推到远程(如无远程主分支则创建，用于初始化远程仓库)  
push后会提示输入远程仓库的账号和密码  
  
==========================5  
再提交之前（push）必须先更新本地库（pull），否则会报错  
抓取远程仓库所有分支更新并合并到本地， merge到本地  
git pull <远程主机名> <远程分支名>:<本地分支名>  
git pull origin master
  
git fetch命令用于从另一个存储库下载对象和引用。不会自动合并。  
git fetch origin master  
  
  
  
**************  
==============  
git status，就可以看到这俩文件已经加上去了  
git status -s  
  
==================  
git rm <file> # 从版本库中删除文件  
git branch 会列出你在本地的分支  
  
git rm <file> --cached # 从版本库中删除文件，但不删除文件  
==========================  
  
  
================  
git reset <file> # 从暂存区恢复到工作文件  
git reset -- . # 从暂存区恢复到工作文件  
git reset --hard # 恢复最近一次提交过的状态，即放弃上次提交后的所有本次修改  
git reset -hard 版本号   
git reset –hard HEAD^(把当前的版本回退到上1个版本)   
git reset –hard HEAD^^(把当前的版本回退到上上1个版本)  
git reset –hard HEAD~100(把当前的版本回退到上100个版本)   

git br -d <branch> # 删除某个分支  
git br -D <branch> # 强制删除某个分支 (未被合并的分支被删除的时候需要强制)  
  
撤销上一次push操作，唯一的办法就是本地修正然后又强制推送，即：  
git reset --hard 然后 git push -f

==========================  
  
