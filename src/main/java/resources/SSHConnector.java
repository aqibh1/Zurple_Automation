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
            shell = new SshByPassword("127.0.0.1", 2200, "vazotov", "ipW6ptsH");
            String stdout = new Shell.Plain(shell).exec(command);
            return stdout;
        }
        catch (java.io.IOException e)
        {
            return e.getMessage();
        }
    }
}
