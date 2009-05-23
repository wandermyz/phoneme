package algorithm;

import java.util.Iterator;

import entity.UserInfo;

public class SimpleUserInfoMatcherUsingLCSQ implements Matcher {

	@Override
	public boolean match(Object pattern, Object target) {
		//ƥ������UserInfo
		if((pattern instanceof UserInfo)||(target instanceof UserInfo)){
			if(!(pattern instanceof UserInfo)||!(target instanceof UserInfo))
				return false;
			else{
				UserInfo patInfo=(UserInfo)pattern;
				UserInfo tarInfo=(UserInfo)target;
				//��ID��ͬ��ֱ�ӷ�����
				if(patInfo.getBaseInfo().getID().getValue()==tarInfo.getBaseInfo().getID().getValue())
					return true;
				//ID��ͬ���ƥ���ֶ�
				int totalCnt=0;
				int matchCnt=0;
				Iterator<String> iter=patInfo.getBaseInfo().getKeySet().iterator();
				while(iter.hasNext()){
					String field=iter.next();
					if(patInfo.getBaseInfo().getInfoField(field)!=null){
						String p = patInfo.getBaseInfo().getInfoField(field).getStringValue();
						String t = tarInfo.getBaseInfo().getInfoField(field).getStringValue();
						totalCnt += p.length();
						matchCnt += StringFunc.lcst(p, t);
					}
				}
				iter=patInfo.getCustomInfo().getKeySet().iterator();
				while(iter.hasNext()){
					String field=iter.next();
					if(patInfo.getCustomInfo().getInfoField(field)!=null){
						String p = patInfo.getCustomInfo().getInfoField(field).getStringValue();
						String t = tarInfo.getCustomInfo().getInfoField(field).getStringValue();
						totalCnt += p.length();
						matchCnt += StringFunc.lcst(p, t);
					}
				}
				totalCnt = Math.max(totalCnt, 1);
				double matchRate=(double)(matchCnt/totalCnt);
				if(matchRate>=0.5)
					return true;
				else
					return false;
			}
		}
		//TODO ����Objectƥ��
		return false;
	}

}