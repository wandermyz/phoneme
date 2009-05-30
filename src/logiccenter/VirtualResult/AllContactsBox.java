package logiccenter.VirtualResult;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import logiccenter.LogicCenter;

import entity.BaseUserInfo;
import entity.CustomUserInfo;
import entity.Group;
import entity.ID;
import entity.MyRemoteException;
import entity.UserInfo;
import entity.infoField.InfoFieldName;
import entity.infoField.Relation;

/**
 * ���Ի�ȡ��ǰ������ϵ�˵�Virtual Proxy������ {@link MessageBox}
 * 
 * @author Administrator
 * 
 */
public class AllContactsBox extends VirtualResult {
	private LogicCenter center;
	private Map<ID, UserInfo> contacts;

	class GetThread extends Thread {
		@Override
		public void run() {
			contacts = new HashMap<ID, UserInfo>();
			List<UserInfo> temp = center.getDataCenter().getAllUserInfo(null);
			for(UserInfo info: temp)
				contacts.put(info.getBaseInfo().getID(), info); 
			List<ID> perIDList = center.getDataCenter().getAllPerContactsID();
			Set<ID> synIDSet = (perIDList == null) ? new HashSet<ID>() : new HashSet<ID>(perIDList);
			for (UserInfo contact : contacts.values()) {
				Relation r = new Relation();
				if (synIDSet.contains(contact.getBaseInfo().getID()))
					r.setPersonal(true);
				for (Group g : center.getAllGroupsBox().getGroups())
					if (g.getUserSet().contains(contact.getBaseInfo().getID()))
						r.addGroup(g.getInfoField(
								InfoFieldName.GroupName.name())
								.getStringValue());
				contact.setInfoField(r);
			}
			setUpdateNow();
		}
	}

	public synchronized int getCnt() {
		return contacts.size();
	}

	public List<UserInfo> getContacts() {
		return new ArrayList<UserInfo>(contacts.values());
	}

	public AllContactsBox(LogicCenter center) {
		this.center = center;
		GetThread thread = new GetThread();
		thread.run();
	}

	protected void editContactImp(UserInfo newInfo){		
		//���customInfo��null����ô���ı�ԭ����customInfo
		UserInfo oldInfo = contacts.get(newInfo.getBaseInfo().getID());
		if (oldInfo != null && newInfo.getCustomInfo() == null)
			newInfo.setCustomInfo(oldInfo.getCustomInfo());
		if (newInfo.getCustomInfo() == null)
			newInfo.setCustomInfo(new CustomUserInfo());
		
		Set<ID> synIDSet = new HashSet<ID>(center.getDataCenter()
				.getAllPerContactsID());
		Relation r = new Relation();
		if (synIDSet.contains(newInfo.getBaseInfo().getID()))
			r.setPersonal(true);
		for (Group g : center.getAllGroupsBox().getGroups())
			if (g.getUserSet().contains(newInfo.getBaseInfo().getID()))
				r.addGroup(g.getInfoField(InfoFieldName.GroupName.name())
						.getStringValue());
		newInfo.setInfoField(r);
		contacts.put(newInfo.getBaseInfo().getID(), newInfo);
	}
	/**
	 * �÷���Ӧ����LogicCenter���޸��Ժ���õģ� GUI����������޸ģ�������LogicCenter�Ľӿ����޸�
	 * 
	 * @param newInfo
	 */
	public synchronized void editContact(UserInfo newInfo) {
		editContactImp(newInfo);
		setUpdateNow();
	}

	public synchronized void removeContact(ID uid) {
		contacts.remove(uid);
		setUpdateNow();
	}

	public synchronized void updateAll() {
		GetThread thread = new GetThread();
		thread.run();
	}
	
	public synchronized Map<ID, UserInfo> getContactsMap(){
		return contacts;
	}
	
	/**
	 * д��������Ϊ�˼���AllContactsBox����״̬�Ĵ�����
	 * ����������д��LogicCenter���������ط�����ôÿ
	 * �¼�һ���û���������AllContactBox����һ��״̬��
	 * @param g
	 */
	public synchronized void updateGroupMembers(Group g) throws RemoteException, MyRemoteException{
		List<ID> newIDList = new ArrayList<ID>();
		for(ID id: g.getUserSet())
			if (!contacts.containsKey(id))
				newIDList.add(id);
		List<BaseUserInfo> newUsers = center.getServer().getContactsInfo(center.getLoginUser().getID(), newIDList);
		for(BaseUserInfo bInfo: newUsers){
			UserInfo newInfo = new UserInfo(bInfo);
			newInfo.setCustomInfo(null);//���ı䱾���ֶ�
			editContactImp(new UserInfo(bInfo));
		}
		setUpdateNow();
	}
	
	public synchronized void updateRelation(List<ID> idList){
		for(ID id: idList)
			if (contacts.containsKey(id))
				editContactImp(contacts.get(id));
		setUpdateNow();
	}
}