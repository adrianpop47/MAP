package runners;

public class DelayTaskRunner extends AbstractTaskRunner{
    public DelayTaskRunner(TaskRunner taskRunner) {
        super(taskRunner);
    }


    @Override
    protected void afterExecution() {
        try {Thread.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
    }
}
