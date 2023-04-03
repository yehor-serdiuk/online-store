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
#=============================================================

# in terminal to build etc.
#mvn compile
#mvn package
#mvn install

#..../project/egor$ mvn clean package

..../project/egor$ mvn clean install
cd ./app/target

# it doesn't work. I'll fix it!
java -cp ~/.m2/repository/egor/test/lib/1.0-SNAPSHOT/lib-1.0-SNAPSHOT.jar -jar ./app-1.0-SNAPSHOT.jar
export CLASSPATH=../../lib/target/lib-1.0-SNAPSHOT.jar & java -jar ./app-1.0-SNAPSHOT.jar


# it works! run in the root of the repo
java -classpath ".\lib\target\lib-1.0-SNAPSHOT.jar;.\app\target\app-1.0-SNAPSHOT.jar" egor.my.app.App