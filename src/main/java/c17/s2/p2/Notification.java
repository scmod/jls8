package c17.s2.p2;

public class Notification {

	public static void main(String[] args) {
		Notification n = new Notification();

		Thread.currentThread().setName("imhere");
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				synchronized (n) {
					n.notify();
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				//都是锁在n.this上,而不是n.lock
				synchronized (n) {
					n.notify();
				}
			}
		}).start();
		n.method();
	}

	private Object lock = new Object();

	void method() {
		try {
			synchronized (this) {
				wait();
				System.out.println("waiting on this");
				synchronized (lock) {
					wait();//这个wait实际上还是this,跟lock无关.不是lock.wait
					System.out.println("waiting on lock");
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
