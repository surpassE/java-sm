package com.sirding.testutil.serialize;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.sirding.core.utils.SerializeUtil;

public class TestSerializeUtil {
	Logger logger = Logger.getLogger(getClass());

	@Test
	public void test1(){
		byte[] buf = SerializeUtil.serialize(getB());
		logger.debug(buf.length);	//186
		B b = SerializeUtil.unSerialize(buf, B.class);
		logger.debug(b.getName());
		
		buf = SerializeUtil.serialize(getA());
		logger.debug(buf.length);	//301
		A a = SerializeUtil.unSerialize(buf, A.class);
		logger.debug(a.getB().getName());
	}
	
	@Test
	public void test2(){
		byte[] buf = SerializeUtil.serializeFst(getB());
		logger.debug("=====length:" + buf.length);	//37
		B b = SerializeUtil.unSerializeFst(buf, B.class);
		logger.debug(b.getName());
		
		buf = SerializeUtil.serializeFst(getA());
		logger.debug("=====length:" + buf.length);	//52
		A a = SerializeUtil.unSerializeFst(buf, A.class);
		logger.debug(a.getName());
	}
	
	@Test
	public void test3(){
		byte[] buf = SerializeUtil.serializeKryo(getB());
		logger.debug("=====length:" + buf.length);	//35
		B b = SerializeUtil.unSerializeKryo(buf, B.class);
		logger.debug(b.getName());
		
		buf = SerializeUtil.serializeKryo(getA());
		logger.debug("=====length:" + buf.length);	//72
		A a = SerializeUtil.unSerializeKryo(buf, A.class);
		logger.debug(a.getName());
	}
	
	private B getB(){
		B b = new B();
		b.setName("zc.ding");
		b.setAge(18);
		return b;
	}
	
	private A getA(){
		A a = new A();
		a.setName("zc.ding.a");
		a.setAge(9);
		B b = getB();
		a.setB(b);
		return a;
	}
}
