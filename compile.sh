find . -name "*.java" > sources.txt
javac -d bin -sourcepath src/com/avaj/AvajLauncher.java @sources.txt
