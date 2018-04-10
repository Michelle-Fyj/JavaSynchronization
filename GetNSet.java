import java.util.concurrent.atomic.AtomicIntegerArray;

class GetNSet implements State{
	private AtomicIntegerArray value;
	private byte maxval;

	GetNSet(byte[] v)
	{
		int[] ints = new int[v.length];
		for (int i = 0; i < v.length; i++)
			ints[i] = v[i];
		value = new AtomicIntegerArray(ints);
		maxval = 127;
	}

	GetNSet(byte[] v, byte m)
	{
		int[] ints = new int[v.length];
		for (int i = 0; i < v.length; i++)
			ints[i] = v[i];
		value = new AtomicIntegerArray(ints);
		maxval = m;
	}

	public int size()
	{
		return value.length();
	}

	public byte[] current()
	{
		byte[] copy = new byte[value.length()];
		for(int i = 0; i < value.length(); i++)
		{
			copy[i] = (byte)value.get(i);
		}
		return copy;
	}

	public boolean swap(int i, int j)
	{
	    int I = value.get(i);
	    int J = value.get(j);
	    if(I <= 0 || J >= maxval)
		{
			return false;
		}
	    value.set(i, --I);
	    value.set(j, ++J);
	    return true;
	}
}
