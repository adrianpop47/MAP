package factory;

import containers.Container;
import containers.QueueContainer;
import containers.StackContainer;

public class TaskContainerFactory implements Factory {
    private static Container instance = null;

    private static Container createContainer(Strategy strategy){
        if(Strategy.LIFO == strategy)
        {
            return new StackContainer();
        }
        else{
            return new QueueContainer();
        }
    }


    @Override
    public Container getInstance(Strategy strategy) {
        if(instance == null)
        {
            instance = createContainer(strategy);
        }
        return instance;
    }
}
