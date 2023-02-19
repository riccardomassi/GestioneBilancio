javac -d ./bin/ -cp ./bin:./lib/jdatepicker-1.3.4.jar ./src/model/*.java ./src/view/File/*.java ./src/view/Panel/*.java ./src/view/Table/*.java ./src/view/*.java
java --enable-preview --class-path bin:./lib/jdatepicker-1.3.4.jar view.Bilancio

