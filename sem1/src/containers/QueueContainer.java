package containers;

import domain.Task;

public class QueueContainer extends AbstractContainer {

    public QueueContainer()
    {
        super();
    }

    @Override
    public void add(Task task) {
        Task[] result = new Task[tasks.length+1];
        result[0] = task;
        System.arraycopy(tasks, 0, result, 1, tasks.length);
        System.arraycopy(result, 0, tasks, 0, tasks.length);
        size++;
    }
}
