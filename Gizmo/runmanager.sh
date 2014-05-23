pushd .
cd WebContent/WEB-INF
java -cp "./classes:./lib/*" edu.cmu.gizmo.management.taskmanager.TaskManager
popd
