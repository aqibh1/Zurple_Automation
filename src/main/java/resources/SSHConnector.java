package resources;

import com.jcabi.ssh.Shell;
import com.jcabi.ssh.Ssh;
import com.jcabi.ssh.SshByPassword;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SSHConnector
{
    public static String runRemoteScript(String command)
    {
        Shell shell = null;
        try
        {
            ConfigReader configReader = ConfigReader.load();
            if ( "".equals(configReader.getPropertyByName("ssh_key")))
            {
                shell = new SshByPassword(configReader.getPropertyByName("ssh_host"), Integer.parseInt(configReader.getPropertyByName("ssh_port")), configReader.getPropertyByName("ssh_user"), configReader.getPropertyByName("ssh_pass"));
            }else
            {
                String privateKey = readKey(configReader.getPropertyByName("ssh_key"));

                if ( "".equals(configReader.getPropertyByName("ssh_pass")))
                {
                    shell = new Ssh(configReader.getPropertyByName("ssh_host"), Integer.parseInt(configReader.getPropertyByName("ssh_port")), configReader.getPropertyByName("ssh_user"), privateKey);
                }else
                {
                    shell = new Ssh(configReader.getPropertyByName("ssh_host"), Integer.parseInt(configReader.getPropertyByName("ssh_port")), configReader.getPropertyByName("ssh_user"), privateKey,configReader.getPropertyByName("ssh_pass"));
                }
            }

            String stdout = new Shell.Plain(shell).exec(command);
            return "OK";
        }
        catch (java.io.IOException e)
        {
            return e.getMessage();
        }
    }

    private static String readKey(String filePath)
    {
        String content = "";
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content;
    }
}
