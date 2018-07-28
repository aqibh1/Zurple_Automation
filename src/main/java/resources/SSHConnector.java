package resources;

import com.jcabi.ssh.Shell;
import com.jcabi.ssh.Ssh;
import com.jcabi.ssh.SshByPassword;
import java.net.UnknownHostException;

public class SSHConnector
{
    public static String runRemoteScript(String command)
    {
        Shell shell = null;
        try
        {
            ConfigReader configReader = ConfigReader.load();
            //shell = new SshByPassword(configReader.getPropertyByName("ssh_host"), Integer.parseInt(configReader.getPropertyByName("ssh_port")), configReader.getPropertyByName("ssh_user"), configReader.getPropertyByName("ssh_pass"));
            shell = new SshByPassword("localhost", 22, "vzotov", "ipW6ptsH");
            String stdout = new Shell.Plain(shell).exec(command);
            return stdout;
        }
        catch (java.io.IOException e)
        {
            return e.getMessage();
        }
    }
}
