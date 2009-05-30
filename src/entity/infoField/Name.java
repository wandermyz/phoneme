package entity.infoField;

public class Name extends EmptyName {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3189490709293556137L;
	private static final int maxLength = 100;
	private static final String nullName = "NoName";
	private String name;

	public static boolean check(String name){
		//TODO ��ʱ��λ���������ļ��
		return (name != null && name.length() > 0 && name.length() <= maxLength);
	}
	
	public Name(String name)
	{
		if (!check(name))
			name = nullName;
		this.name=name;
	}
	
	public void setStringValue(String name)
	{
		if (!check(name))
			name = nullName;
		this.name=name;
	}

	@Override
	public String getStringValue() {
		return this.name;
	}

	@Override
	public boolean isEmpty() {
		return name.equals(nullName);
	}
}