package entity.infoField;

/**
 * 固定电话，关于多个电话的问题参考{@link EmptyCellphone}
 * @author Administrator
 *
 */
public class EmptyPhone extends EmptyInfoField implements IndexedInfoField 
{

	@Override
	public String getName() {
		return "Phone";
	}

}
