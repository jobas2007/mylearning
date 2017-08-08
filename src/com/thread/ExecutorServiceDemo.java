package com.thread;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceDemo {
	public static void main(String args[]) {
		ExecutorService directoryMonitoringThreadPool = Executors
				.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		TaskManager taskManager = new TaskManager(new DirectoryMonitoringService());

		List<DirectoryWatcherTask> partitionMonitoringTasks;
		try {
			partitionMonitoringTasks = taskManager.getPartitionMonitoringTasks();
			partitionMonitoringTasks.stream().forEach(task -> {
				directoryMonitoringThreadPool.submit(task);
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @author Arpit Singhai
	 *
	 */
	public static class DirectoryWatcherTask implements Runnable {

		private final Path dir;
		private final WatchService watcher;
		private final WatchKey key;

		/**
		 * Creates a WatchService and registers the given directory
		 */
		public DirectoryWatcherTask(Path dir) throws IOException {
			this.dir = dir;
			this.watcher = FileSystems.getDefault().newWatchService();
			this.key = dir.register(watcher, ENTRY_CREATE);
		}

		@SuppressWarnings("unchecked")
		static <T> WatchEvent<T> cast(WatchEvent<?> event) {
			return (WatchEvent<T>) event;
		}

		public void run() {
			try {
				for (;;) {
					// wait for key to be signalled
					WatchKey key = watcher.take();

					if (this.key != key) {
						System.err.println("WatchKey not recognized!");
						continue;
					}

					for (WatchEvent<?> event : key.pollEvents()) {
						WatchEvent<Path> ev = cast(event);
						System.out.format("%s: %s\n", ev.kind(), dir.resolve(ev.context()));
						// TODO: handle event. E.g. call listeners
					}

					// reset key
					if (!key.reset()) {
						break;
					}
				}
			} catch (InterruptedException x) {
				return;
			}
		}
	}

	/**
	 * 
	 * @author arpitsinghai
	 *
	 */
	public static class DirectoryMonitoringService {

	}

	/**
	 * 
	 * @author arpitsinghai
	 *
	 */
	public static class TaskManager {

		private DirectoryMonitoringService directoryMonitoringService;

		private int numberOfThreads = Runtime.getRuntime().availableProcessors();

		public TaskManager(DirectoryMonitoringService directoryMonitoringService) {
			this.directoryMonitoringService = directoryMonitoringService;
		}

		public List<DirectoryWatcherTask> getPartitionMonitoringTasks() throws InterruptedException {
			// List<File> directoryContent =
			// directoryMonitoringService.getDirectoryContent();

			// TODO partition the list
			// ReaderTask readerTask=new
			// ReaderTask(directoryContent.stream().collect(Collectors.parti);

			List<DirectoryWatcherTask> directoryWatcherTasks = new ArrayList<>();
			// directoryWatcherTasks.add(new DirectoryWatcherTask(""));
			return directoryWatcherTasks;
		}

		public int getNumberOfThreads() {
			return numberOfThreads;
		}

	}

}
