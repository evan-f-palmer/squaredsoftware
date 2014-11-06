package com.squaredsoftware.lua.libs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.jse.JseOsLib;

public class LuaOSLib extends JseOsLib{
	protected Varargs execute(String command) {
		int exitValue = 0;
		StringBuffer result = new StringBuffer();
		ProcessBuilder pb;
		Process shell;

		pb = buildProcessBasedOnOS(command);
		pb.redirectErrorStream(true);
		
		try {
			shell = pb.start();
			InputStream shellIn = shell.getInputStream();
			exitValue = shell.waitFor();
			getResult(result, shellIn);
			shellIn.close();
		} catch (IOException e1) {
			exitValue = EXEC_IOEXCEPTION;
		} catch (InterruptedException e) {
			exitValue = EXEC_INTERRUPTED;
		}
		
		if (exitValue == 0) {
			return varargsOf(TRUE, valueOf(result.toString()), valueOf(0));
		} else {
			return varargsOf(NIL, valueOf(""), valueOf(exitValue));
		}
	}

	private void getResult(StringBuffer result, InputStream shellIn)
			throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(shellIn));
		String line = "";
		while ((line = reader.readLine()) != null) {
			result.append(line);
		}
	}

	private ProcessBuilder buildProcessBasedOnOS(String command) {
		ProcessBuilder pb;
		if(System.getProperty("os.name").startsWith("Linux")) {
			pb = new ProcessBuilder("bash", "-c", command);
		} else {
			pb = new ProcessBuilder(command);
		}
		return pb;
	}
}
