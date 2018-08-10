package resources;

import com.jcabi.ssh.Shell;
import com.jcabi.ssh.Ssh;
import com.jcabi.ssh.SshByPassword;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
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
            String project_path = System.getProperty("user.dir");
            String privateKey = readKey(project_path + configReader.getPropertyByName("ssh_key"));

            shell = new Ssh(configReader.getPropertyByName("ssh_host"), Integer.parseInt(configReader.getPropertyByName("ssh_port")), configReader.getPropertyByName("ssh_user"), privateKey);
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
