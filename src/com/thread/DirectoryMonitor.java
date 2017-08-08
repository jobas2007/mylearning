package com.thread;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.LinkedList;

public class DirectoryMonitor {
	public static void main(String[] args) throws InterruptedException {
		// Object of a class that has both produce()
		// and consume() methods
		final PC pc = new PC();
		final String dirPath = "<< dir path>>";

		// Create producer thread
		final Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					pc.produce(dirPath);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		// Create consumer thread
		final Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					pc.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		// Start both threads
		t1.start();
		t2.start();

		// t1 finishes before t2
		t1.join();
		t2.join();
	}

	// This class has a list, producer (adds items to list and consumer (remove
	// items).
	public static class PC {

		// Create a list shared by producer and consumer
		// Size 1 to consume only single file at a time
		final LinkedList<File> list = new LinkedList<>();
		final int capacity = 1;

		/**
		 * Function called by producer thread. It starts the watcher service on the
		 * directory,along with polling for registered event.
		 * 
		 * @param dirPath
		 * @throws InterruptedException
		 */
		public void produce(final String dirPath) throws InterruptedException {
			// String dirPath = "";
			while (true) {
				synchronized (this) {
					// producer thread waits while list is full
					while (list.size() == capacity) {
						wait();
					}

					// to insert the jobs in the list
					final Path folder = Paths.get(dirPath);
					final WatchKey key = watchDirectoryPath(folder).take();

					// Dequeueing events
					Kind<?> kind = null;
					for (WatchEvent<?> watchEvent : key.pollEvents()) {
						// Get the type of the event
						kind = watchEvent.kind();
						if (OVERFLOW == kind) {
							continue; // loop
						} else if (ENTRY_CREATE == kind) {

							// A new Path was created
							final Path newPath = ((WatchEvent<Path>) watchEvent).context();

							System.out.println("Producer produced-" + newPath);
							list.add(newPath.toFile());

							// notifies the consumer thread that now it can start consuming
							notify();

							// after notifying
							Thread.sleep(1000);
						}
					} // end of for
				} // end of sync block
			}
		}

		// Function called by consumer thread
		public void consume() throws InterruptedException {
			while (true) {
				synchronized (this) {
					// consumer thread waits while list is empty
					while (list.size() == 0) {
						wait();
					}

					// to retrieve the first job in the list
					File file = list.removeFirst();
					System.out.println("Consumer consumed-" + file);

					// Wake up producer thread
					notify();

					// and sleep
					Thread.sleep(1000);
				}
			}
		}
	}

	/**
	 * Register's CREATE event on the directory, to set up monitor.
	 * 
	 * @param path
	 *            the directory path.
	 * @return the watch service instance.
	 */
	private static WatchService watchDirectoryPath(final Path path) {
		System.out.println("Path:" + path);

		// Sanity check - Check if path is a folder
		try {
			final Boolean isFolder = (Boolean) Files.getAttribute(path, "basic:isDirectory", NOFOLLOW_LINKS);
			if (!isFolder) {
				throw new IllegalArgumentException("Path: " + path + " is not a folder");
			}
		} catch (IOException ioe) {
			// Folder does not exists
			ioe.printStackTrace();
		}

		System.out.println("Watching path: " + path);
		final FileSystem fs = path.getFileSystem();

		// creating watcher service
		WatchService watchService = null;
		try {
			watchService = fs.newWatchService();

			// register create event
			path.register(watchService, ENTRY_CREATE);

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return watchService;
	}
}