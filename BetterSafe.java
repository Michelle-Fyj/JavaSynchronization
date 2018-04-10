import java.util.concurrent.locks.ReentrantLock;

    class BetterSafe implements State {
    private byte[] value;
    private byte maxval;
    private ReentrantLock wait;

    BetterSafe(byte[] v)
    {
	value = v;
	maxval = 127;
	wait = new ReentrantLock();
    }

    BetterSafe(byte[] v, byte m)
    {
	value = v;
	maxval = m;
	wait = new ReentrantLock();
    }

    public int size()
    {
        return value.length;
    }

    public byte[] current()
    {
	return value;
    }

    public boolean swap(int i, int j) 
    {
        wait.lock();
	if (value[i] <= 0 || value[j] >= maxval) {
	    wait.unlock();
		return false;
	}
	value[i]--;
	value[j]++;
        wait.unlock();
	return true;
    }
}
