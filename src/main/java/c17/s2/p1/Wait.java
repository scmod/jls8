package c17.s2.p1;

/**
 * 为了防止spurious wake-ups(虚假唤醒),wait方法总是要在loop中使用 this method should always be
 * used in a loop synchronized (obj) { while (<condition does not hold>)
 * obj.wait(timeout); ... // Perform action appropriate to condition }
 * 
 * @author John Smith
 *
 */
public class Wait {

	public static void main(String[] args) {
		// new Wait().method1();

		// new Wait().method2();

		Thread.currentThread().setName("imhere");
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				Thread t = Thread.getAllStackTraces().keySet().stream()
						.filter(x -> "imhere".equals(x.getName())).findFirst()
						.get();
				t.interrupt();
				// 这里还能拿到true
				System.out.println(t.isInterrupted());
			}
		}).start();
		new Wait().method3();

	}

	/**
	 * if the current thread is not the owner of the object's monitor.
	 * Throws:IllegalMonitorStateException
	 * 
	 * thread t does not already possess the lock for target m, then an
	 * IllegalMonitorStateException is throw m = this, t = currentThread, n =
	 * lockActions
	 */
	void method1() {
		try {
			// n = 0;IllegalMonitorStateException
			// synchronized (this) {
			// wait();
			// }
			// n = 2;
			synchronized (this) {
				synchronized (this) {
					wait(1000);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * If this is a timed wait and the nanosecs argument is not in the range of
	 * 0-999999 or the millisecs argument is negative, then an
	 * IllegalArgumentException is thrown
	 */
	void method2() {
		try {
			synchronized (this) {
				// wait(-1);
				wait(0, 9999999);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * If thread t is interrupted, then an InterruptedException is thrown and
	 * t's interruption status is set to false 但是这里的status怎么本来就是false.. true if
	 * this thread has been interrupted; false otherwise;
	 * 
	 * Thread.currentThread().isInterrupted()跟Thread.interrupted()不同之处在于
	 * 前者调用后不会改变interrupt status,后者会重置成false
	 */
	void method3() {
		try {
			synchronized (this) {
				wait();
			}
		} catch (InterruptedException e) {
			// 这里catch到之前好像就被set to false了
			System.out.println(Thread.currentThread().isInterrupted());
			e.printStackTrace();
		}
	}

	private boolean flag = true;
	private Object lock = new Object();

	// spurious wakeup?
	void method4() {
		try {
			synchronized (this) {
				if (flag) {
					// 这个wait可能被别的线程的执行导致打断,或者唤醒,但是好像我弄不出
					//来如何才能不用notify或者interrupt掉线程来结束这个wait
					wait();
					synchronized (lock) {
						System.out.println("get lock");
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
