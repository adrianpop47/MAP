package factory;

import containers.Container;

public interface Factory {
    Container getInstance(Strategy startegy);

}

