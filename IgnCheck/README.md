I wrote this right after Minecraft started allowing name changes due to the issues that caused.  We needed a way to check past names in-game and at the time, there were no other plugins that could do that.  Others created similar plugins and released them 2 weeks after I finished this, but mine could still do more than theirs for quite a while.  At the time, I was still very new to programming and everything I knew about Java was self taught from videos online.  The Command class is split up into several files as this was the only way I could manage to have code run semi-async and not freeze up the server while waiting on responses from sites.

[Main.java](https://github.com/tbtaco/Minecraft/blob/main/IgnCheck/src/tbtaco/ignCheck/Main.java) is the starting point.  It gets things set up and has some of the commonly used methods that other classes can call.  It also has a lot of the fields that other classes will use.

[Command.java](https://github.com/tbtaco/Minecraft/blob/main/IgnCheck/src/tbtaco/ignCheck/Command.java) is the start for commands.  This handles the easier commands and runs 1 if we're searching a name.

[CommandRun1.java](https://github.com/tbtaco/Minecraft/blob/main/IgnCheck/src/tbtaco/ignCheck/CommandRun1.java) is called if we're searching a name.  It runs up to 3 times for 3 names.  If we've been given a UUID, we can skip to running 3.  Else run 2.

[CommandRun2.java](https://github.com/tbtaco/Minecraft/blob/main/IgnCheck/src/tbtaco/ignCheck/CommandRun2.java) is called if we have a name but no UUID.  1 created a request so 2 runs repeatedly until a response is received.  Once we have a UUID, call 3.  If a UUID couldn't be obtained, call 5 after creating a request to a 3rd party site for names.

[CommandRun3.java](https://github.com/tbtaco/Minecraft/blob/main/IgnCheck/src/tbtaco/ignCheck/CommandRun3.java) is called to get name history from a UUID.  Calls 4 after running.

[CommandRun4.java](https://github.com/tbtaco/Minecraft/blob/main/IgnCheck/src/tbtaco/ignCheck/CommandRun4.java) is called after creating a request in 3.  This will run repeatedly until a response is received.  This is usually the end of the search.  If in debug mode or forcing a full search, it'll start a request from a 3rd party site to get names and call 5.

[CommandRun5.java](https://github.com/tbtaco/Minecraft/blob/main/IgnCheck/src/tbtaco/ignCheck/CommandRun5.java) is called after creating a request to a 3rd party site.  It will repeatedly run until a response is received.

[Timer.java](https://github.com/tbtaco/Minecraft/blob/main/IgnCheck/src/tbtaco/ignCheck/Timer.java) is used for debugging.
