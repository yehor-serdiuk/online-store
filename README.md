mkdir ./test_project
cd ./test_project
git clone https://gitlab.com/volcanic_cupcake/repository.git .
#===============================================================
git branch egor
git checkout egor
#===============================================================
# create maven project structure
#===============================================================

mvn archetype:generate

Define value for property 'groupId': egor.test
Define value for property 'artifactId': test-app
Define value for property 'version' 1.0-SNAPSHOT: :
Define value for property 'package' egor.test: : egor.my.app
Confirm properties configuration:
groupId: egor.test
artifactId: test-app
version: 1.0-SNAPSHOT
package: egor.my.app
Y

for the rest just press Enter

mv ./test-app/* .
rmdir ./test-app/

#=============================================================
# open in idea
#
