package c17.s3;

public class SleepAndYield {

	boolean flag = true;

	public static void main(String[] args) {
		SleepAndYield say = new SleepAndYield();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					say.flag = false;
				} catch (InterruptedException e) {
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (say.flag) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					System.out.println("looping...");
				}
			}
		}).start();

	}

}
