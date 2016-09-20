package com.sirding.pdf;

import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
 
/**
 * Simple List example.
 */
public class Yxjh {
        public static final String DEST = "c:/pdf/yxjh";
        public static PdfFont cnFont = null;
        public static Image sealImg = null;
        public static Image yrSealImg = null;
        
//        public static PdfWriter writer = null;
//        public static PdfDocument pdf = null;
//        public static Document document = null;
        
    public static void main(String args[]) throws IOException {
//        File file = new File(DEST);
//        file.getParentFile().mkdirs();
        new Yxjh().createPdf(DEST);
    }
 
    public void createPdf(String dest) throws IOException {
    	for(int j =0 ;j<2;j++){
    		PdfWriter writer = new PdfWriter(dest+j+".pdf");
    		PdfDocument pdf = new PdfDocument(writer);
    		
    		Document document = new Document(pdf);
    		document.add(createYxTitle("（第yyy-160727785期）"));
    		document.add(createParagraph("协议编号：___000886104160825104246___",10).setTextAlignment(TextAlignment.RIGHT));
    		
    		Paragraph p1 = new Paragraph();
    		p1.setFont(getCnFont());
    		p1.setBold();
    		p1.setFontSize(12);
    		p1.add(new Text("甲方： 王欣欣 \n"));
    		p1.add(new Text("真实姓名：王欣欣 \n"));
    		p1.add(new Text("身份证件号码： 130622199111062441 \n"));
    		p1.add(new Text("联系电话： 13146946684 \n"));
    		p1.add(new Text("邮箱：wupeng6684@163.com"));
    		document.add(p1);
    		
    		Paragraph p = new Paragraph();
    		p.setFont(getCnFont());
    		p.setBold();
    		p.setFontSize(12);
    		Image img = createYrSeal();
    		img.setWidth(100);
    		img.setHeight(100);
    		img.setFixedPosition(80, 560);
//    	img.setRelativePosition(80, -120, 100, 0);
    		Text t1 = new Text("乙方：北京亿润财富科技服务有限公司 \n");
    		Text t2 = new Text("地址：北京市大兴区西红门鸿坤广场B1座4层 \n");
    		Text t3 = new Text("邮编：10000 \n");
    		Text t4 = new Text("咨询电话：400-696-9598 \n");
    		
    		p.add(t1);
    		p.add(t2);
    		p.add(t3);
    		p.add(t4);
//        p.add(img);
    		document.add(p);
    		document.add(img);
    		
    		document.add(createParagraphIndent("乙方为运营乾坤袋平台（其网址为www.51qiankundai.com）的运营管理人，系提供金融撮合服务的居间平台服务商。"));
    		document.add(createParagraphIndent("甲方系具备完全民事行为能力的自然人、法人或其他组织，其资金来源为自有合法资金。"));
    		document.add(createParagraphIndent("甲乙双方本着平等自愿、诚实信用的原则，达成如下协议："));
    		
    		document.add(createFirstTile("一、    本期服务项目要素"));
    		document.add(createParagraphIndent("甲方同意按照如下条件通过乾坤袋平台加入乙方提供的本期服务计划，相关条件详情如下："));
    		document.add(createParagraph("1.   预期年化收益率【9%】", 12));
    		document.add(createParagraph("2.   甲方加入本金数额【4800.00元】", 12));
    		document.add(createParagraph("3.   锁定期【3月】", 12));
    		document.add(createParagraph("4.   收益处理方式【收益返还】", 12));
    		document.add(createParagraph("5.   锁定期开始日【2016年07月14日】", 12));
    		document.add(createParagraph("6.   锁定期结束日【2016年10月14日】", 12));
    		
    		document.add(createBorderParagraph("甲方已经知悉、了解并同意："));
    		
    		document.add(createParagraph("1.   本协议约定投资范围内的借款标的均适用于乾坤袋《保障计划》。", 12));
    		document.add(createParagraph("2.   甲方支付完毕加入资金的，即视为加入本期服务计划；", 12));
    		document.add(createParagraph("3.   锁定期内，甲方加入本期服务计划的资金不能提现。", 12));
    		
    		document.add(createFirstTile("二、    本期服务计划加入条件"));
    		document.add(createParagraph("1.   对象：所有乾坤袋平台的注册用户。", 12));
    		document.add(createParagraph("2.   已知悉并理解本平台法律声明。", 12));
    		document.add(createParagraph("3.   已知悉本期服务计划规则，并同意本协议中的各项条款。", 12));
    		
    		document.add(createFirstTile("三、    本期服务计划甲方对乙方的授权"));
    		document.add(createBorderParagraph("甲方已经知悉、了解，并在此无条件、不可撤销地同意并确认："));
    		document.add(createParagraph("1.   自甲方加入本期服务计划起，甲方即授权乙方在本协议项下甲方认可的投资范围内为甲方提供匹配投标服务，甲方将通过乾坤袋网站系统与相关债务人签署相关借款协议；", 12));
    		document.add(createParagraph("2.   甲方对此等匹配投标和签署相关借款协议之安排已充分知悉并理解；", 12));
    		document.add(createParagraph("3.   该等签署的借款协议均视为甲方的真实意思表示，甲方对该等法律文件的效力均予以认可且无任何异议，并无条件接受该等签署的借款协议之约束；", 12));
    		document.add(createParagraph("4.   甲方在乙方为其匹配投标并在甲方签署之借款协议等法律文件或其中的相关条款生效后，乙方即可根据该等协议和本协议相关约定，授权第三方支付机构对相关款项进行冻结、划扣、支付以及行使其他权利，甲方对此均予以接受和认可。", 12));
    		
    		document.add(createFirstTile("四、    甲方预期收益及支付"));
    		document.add(createParagraph("1.   甲方加入服务计划的资金每期产生的预期收益在进入甲方账户后将作为甲方的可支配收益。", 12));
    		document.add(createParagraph("2.   甲方的投资本金不得提前赎回。", 12));
    		
    		document.add(createFirstTile("五、    退出机制"));
    		document.add(createParagraph("1.   甲方加入的本期服务计划到期后，甲方授权乙方为其提供债权转让服务。", 12));
    		document.add(createParagraph("2.   甲方加入的本期服务计划到期后，乙方开始为甲方提供债权转让服务。债权最终转让完毕的时间以在乾坤袋平台进行转让的实际交易情况为准，乙方不对该等债权转让完成时间做出任何承诺和保证。", 12));
    		
    		document.add(createFirstTile("六、    税费"));
    		document.add(createParagraphIndent("甲方应自行承担并缴付其因加入本期服务计划所获收益的应纳税额。"));
    		
    		document.add(createFirstTile("七、    甲方声明和保证"));
    		document.add(createParagraph("1.   在签署本协议书以前，甲方已认真阅读本协议有关条款，乙方已就本协议书及有关交易文件的全部条款和内容提供各种条件、利用各种方式向甲方进行了详细的说明和解释，对有关条款不存在任何疑问或异议，并对协议双方的权利、义务、责任与风险有清楚和准确的理解。", 12));
    		document.add(createParagraph("2.   甲方保证所使用的资金为合法取得，且甲方对该等资金拥有完全且无瑕疵的处分权。", 12));
    		document.add(createParagraph("3.   甲方保证为履行本协议而向乙方提供的全部资料均真实、有效。", 12));
    		document.add(createParagraph("4.   本服务协议和甲方通过点击确认与乙方签署的用户注册协议等相关协议，以及乙方在乾坤袋网站不时公示之交易规则、说明、公告等涉及甲乙双方权利义务的法律文件，共同构成了约束甲方接受乙方在本协议项下提供的服务时双方行为的协议的全部，甲方均须予以遵守，如有违反，应当自行承担相关法律后果。", 12));
    		
//    		document.add(createFirstTile("八、    不可抗力"));
//    		document.add(createParagraph("1.   甲方需要通过乾坤袋平台或客服人员查询等方式了解相关信息。如未及时查询，或由于通讯故障、系统故障以及其他不可抗力等因素影响使甲方无法及时了解相关信息，由此产生的责任和风险由甲方自行承担。", 12));
//    		document.add(createParagraph("2.   由于地震、火灾、战争等不可抗力导致的交易中断、延误的，甲乙双方互不承担责任。但应在条件允许的情况下，应采取一切必要的补救措施以减小不可抗力造成的损失。", 12));
//    		document.add(createFirstTile("九、    其他"));
//    		document.add(createParagraph("1.   本协议自甲方在乾坤袋平台操作确认同意签署本协议并支付完毕加入本金数额，且经乙方确认甲方成功加入本期服务计划时（以甲方的乾坤袋平台账户信息显示为准）生效。", 12));
//    		document.add(createParagraph("2.   本协议采用电子文本形式制成，可以有一份或者多份并且每一份具有同等法律效力，并永久保存在乙方为此设立的专用服务器上备查，对各方均具有法律约束力。", 12));
//    		document.add(createParagraph("3.   本协议中的任何一条或多条违反适用的法律法规，则该条将被视为无效，但该无效条款并不影响本协议其他条款的效力。", 12));
//    		document.add(createParagraph("4.   本协议项下产生的纠纷，双方先行协商解决，协商不成的，任何一方可向乙方住所地有管辖权的人民法院提起诉讼。", 12));
    		
    		document.close();
    		document.flush();
//    		pdf.close();
    		
//    		document = null;
//    		pdf = null;
//    		writer = null;
    	}
    }
    
    private Paragraph createBorderParagraph(String text){
    	 Paragraph para = new Paragraph();
         para.setFont(getCnFont());
         para.setBold();
         para.setFontSize(12);
         para.setFirstLineIndent(24);
         Text bo = new Text(text);
         bo.setBorder(new SolidBorder(Color.BLACK, 1));
         para.add(bo);
         return para;
    }
    
    private Paragraph createParagraph(String text,int size){
    	Paragraph p = new Paragraph(text);
    	p.setFontSize(size);
    	p.setFont(getCnFont());
    	return p;
    }
    
    private Paragraph createYxTitle(String text){
    	 Paragraph p = new Paragraph();
         Text title = new Text("优选计划服务协议");
         title.setBold();
         Text term = new Text(text);
         term.setFontSize(12);
         p.add(title).add(term);
         p.setFont(getCnFont());
         p.setTextAlignment(TextAlignment.CENTER);
         return p;
    }
    /**
     * 
     * @Description: 创建合同标题
     * @param @param text 标题内容
     * @param @return   
     * @return Paragraph  
     * @throws
     * @author peng.wu
     * @date 2016年8月24日
     */
    private Paragraph createTitle(String text,int size){
    	Paragraph title = new Paragraph(text);
    	title.setTextAlignment(TextAlignment.CENTER);
    	title.setFontSize(size);
    	title.setBold();
    	title.setFont(getCnFont());
//        document.add(title);
        return title;
    }
    
    /**
     * 
     * @Description: 创建合同的一级标题
     * @param @param text 一级标题内容
     * @param @return   
     * @return Paragraph  
     * @throws
     * @author peng.wu
     * @date 2016年8月24日
     */
    private Paragraph createFirstTile(String text){
    	Paragraph firstTile = new Paragraph(text);
    	firstTile.setFontSize(12);
    	firstTile.setBold();
    	firstTile.setFont(getCnFont());
    	return firstTile;
    }
    
    /**
     * 
     * @Description: 创建缩进的段落
     * @param @param text 段落内容
     * @param @return   
     * @return Paragraph  
     * @throws
     * @author peng.wu
     * @date 2016年8月24日
     */
    private Paragraph createParagraphIndent(String text){
    	Paragraph paragraphIndent = new Paragraph(text);
    	paragraphIndent.setFontSize(12);
    	paragraphIndent.setFont(getCnFont());
    	paragraphIndent.setFirstLineIndent(24);
    	return paragraphIndent;
    }
    
    /**
     * 
     * @Description: 获取合同上的公章
     * @param @return   
     * @return Image  
     * @throws
     * @author peng.wu
     * @date 2016年8月24日
     */
    private Image createSeal(){
    	Image img = getSealImg();
    	img.setWidth(100);
    	img.setHeight(100);
    	img.setTextAlignment(TextAlignment.RIGHT);
    	img.setRelativePosition(0, 50, 100, 0);
        return img;
    }
    
    /**
     * 
     * @Description: 获取支持中文的字体，为单例
     * @param @return   
     * @return PdfFont  
     * @throws
     * @author peng.wu
     * @date 2016年8月24日
     */
    private PdfFont getCnFont(){
		try {
			if(cnFont == null){
				cnFont = PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H", false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return cnFont;
    }
    
    /**
     * 
     * @Description: 获取公章图片
     * @param @return   
     * @return Image  
     * @throws
     * @author peng.wu
     * @date 2016年8月24日
     */
    private Image getSealImg(){
    	try {
    		if(sealImg == null){
    			sealImg = new Image(ImageDataFactory.create("src/main/resources/img/hkjt_gz.png"));
    		}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    	return sealImg;
    }
    
    /**
     * 
     * @Description: 获取亿润财富公章
     * @param @return   
     * @return Image  
     * @throws
     * @author peng.wu
     * @date 2016年8月29日
     */
    private Image getYrSealImg(){
    	try {
    		if(sealImg == null){
    			sealImg = new Image(ImageDataFactory.create("src/main/resources/img/gongzhang.png"));
    		}
    	} catch (MalformedURLException e) {
    		e.printStackTrace();
    	}
    	return sealImg;
    }
    
    /**
     * 
     * @Description: 获取亿润财富公章
     * @param @return   
     * @return Image  
     * @throws
     * @author peng.wu
     * @date 2016年8月29日
     */
    private Image createYrSeal(){
    	Image img = getYrSealImg();
    	img.setWidth(100);
    	img.setHeight(100);
    	img.setTextAlignment(TextAlignment.RIGHT);
    	img.setRelativePosition(0, 50, 100, 0);
        return img;
    }
}
