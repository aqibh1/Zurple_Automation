package resources;

public abstract class AbstractTest
{

    public TestEnvironment getEnvironment()
    {
        Long thread_id = Thread.currentThread().getId();
        TestEnvironment environment = EnvironmentFactory.getEnvironment(thread_id);
        return environment;
    }
    
}
