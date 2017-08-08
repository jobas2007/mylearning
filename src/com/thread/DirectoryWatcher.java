package com.thread;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

public class DirectoryWatcher implements Runnable {

	private final Path dir;
	private final WatchService watcher;
	private final WatchKey key;

	@SuppressWarnings("unchecked")
	static <T> WatchEvent<T> cast(WatchEvent<?> event) {
		return (WatchEvent<T>) event;
	}

	/**
	 * Creates a WatchService and registers the given directory
	 */
	public DirectoryWatcher(Path dir) throws IOException {
		this.dir = dir;
		this.watcher = FileSystems.getDefault().newWatchService();
		this.key = dir.register(watcher, ENTRY_CREATE);
	}

	public void run() {
		String line = null;
		try {
			while (true) {
				// wait for key to be signaled
				WatchKey key = watcher.take();

				if (this.key != key) {
					System.err.println("WatchKey not recognized!");
					continue;
				}
				Kind<?> kind = null;
				for (WatchEvent<?> event : key.pollEvents()) {
					WatchEvent<Path> ev = cast(event);

					kind = ev.kind();
					if (OVERFLOW == kind) {
						continue; // loop
					} else if (ENTRY_CREATE == kind) {
						System.out.println("Create Event Registered..");
						final Path newPath = ((WatchEvent<Path>) ev).context();
						System.out.println(newPath);
						File f = newPath.toFile();
						FileReader fileReader = new FileReader(f);
						BufferedReader bufferedReader = new BufferedReader(fileReader);
						while ((line = bufferedReader.readLine()) != null) {
							System.out.println(line);
						}
						bufferedReader.close();
						f.delete();
					}
				}
				// reset key
				// if (!key.reset()) {
				// break;
				// }

			} // end of for

		} catch (InterruptedException | IOException x) {
			return;
		}
	}

	public static void main(String[] args)
			throws IOException, InterruptedException, ExecutionException, TimeoutException {

		Path dir = Paths.get("<<path to dir>>");
		DirectoryWatcher watcher = new DirectoryWatcher(dir);

		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		Future<?> future = executor.submit(watcher);
		// executor.shutdown();

		// Now, the watcher runs in parallel
		// Do other stuff here

		// Shutdown after 10 seconds
		// executor.awaitTermination(1000, TimeUnit.SECONDS);

		// abort watcher
		// future.cancel(true);

		// executor.awaitTermination(1, TimeUnit.SECONDS);
		// executor.shutdownNow();
	}
}
