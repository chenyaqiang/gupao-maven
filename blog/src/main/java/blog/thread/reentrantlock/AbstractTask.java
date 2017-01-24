package blog.thread.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractTask implements Runnable{
	protected ReentrantLock lock;

	public ReentrantLock getLock() {
		return lock;
	}

	public void setLock(ReentrantLock lock) {
		this.lock = lock;
	}
	

}
