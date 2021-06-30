package fulan.tianjian.demo.web.controller.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import fulan.tianjian.demo.model.web.ResponseValue;
import fulan.tianjian.demo.model.web.vo.InsureConfigVo;
import fulan.tianjian.demo.model.web.vo.InsureHandlePersonVo;
import fulan.tianjian.demo.model.web.vo.PolicyDescribeVo;
import fulan.tianjian.demo.model.web.vo.PolicySchemeConfigVo;
import fulan.tianjian.demo.model.web.vo.PolicyValueViewVo;
import fulan.tianjian.demo.web.service.config.BaseConfigService;

@RestController
@RequestMapping("/config")
public class PolicyConfigController {

	
	@Autowired
	private BaseConfigService baseConfigService;

	@PostMapping("/policy/add")
	public ResponseValue<Boolean> savePolicyConfig(@RequestBody List<PolicySchemeConfigVo> policySchemeVos) {

		baseConfigService.savePolicyConfig(policySchemeVos);

		return ResponseValue.successResponse(true);
	}

	@GetMapping("/policy/find")
	public ResponseValue<List<PolicySchemeConfigVo>> getPolicyConfig(@RequestParam String regionCode) {
		return ResponseValue.successResponse(baseConfigService.getPolicyConfig(regionCode));
	}
	
	/**
	 * 前端缓存
	 * 获取保险描述详情
	 * @return
	 */
	public ResponseValue<List<PolicyDescribeVo>> getPolicyDescribe() {
		
		List<PolicyDescribeVo> policyDescribes = baseConfigService.findPolicyDescribe();

		return ResponseValue.successResponse(policyDescribes);
	}
	
	/**
	 * 前端缓存
	 * 获取保险视图主要是提供保险保额选线等数据
	 * @return
	 */
	public ResponseValue<List<PolicyValueViewVo>> getPolicyView() {
		
		List<PolicyValueViewVo> policyValueViews = baseConfigService.findPolicyValueView();
		
		return ResponseValue.successResponse(policyValueViews);
	}
	
	@PostMapping("/insureConfig/save")
	public ResponseValue<Boolean> saveInsureConfig(@RequestBody InsureConfigVo insureConfigVo) {
		
		baseConfigService.saveInsureConfig(insureConfigVo);
		
		return ResponseValue.successResponse(true);
		
	}
	
	@GetMapping("/insureConfig/find")
	public ResponseValue<InsureConfigVo> findInsureConfig(@RequestParam String provinceCode) {
		
		InsureConfigVo insureConfigVo = baseConfigService.getInsureConfig(provinceCode);
		
		return ResponseValue.successResponse(insureConfigVo);
		
	}
	
	@PostMapping("/insureHandlePerson/save")
	public ResponseValue<Boolean> saveInsureHandlePerson(@RequestBody List<InsureHandlePersonVo> insuredHandlePersonVos) {
		
		baseConfigService.saveInsurePerson(insuredHandlePersonVos);
		
		return ResponseValue.successResponse(true);
		
	}
	
	@GetMapping("/insureHandlePerson/get")
	public ResponseValue<List<InsureHandlePersonVo>> getInsureHandlePerson(@RequestParam String regionCode) {
		
		List<InsureHandlePersonVo> insureHandlePersons = baseConfigService.getInsureHandlePerson(regionCode);
		
		return ResponseValue.successResponse(insureHandlePersons);
		
	}
	
	/**
	 * 北京市（京）:11

天津市（津）:12

上海市（沪）:31

重庆市（渝）:50

河北省（冀）:13

河南省（豫）:41

云南省（云）:53

辽宁省（辽）:21

黑龙江省（黑）:23

湖南省（湘）:43

安徽省（皖）:34

山东省（鲁）:37

新疆维吾尔（新）:65

江苏省（苏）:32

浙江省（浙）:33

江西省（赣）:36

湖北省（鄂）:42

广西壮族（桂）:45

甘肃省（甘）:62

山西省（晋）:14

内蒙古（蒙）:15

陕西省（陕）:61

吉林省（吉）:22

福建省（闽）:35

贵州省（贵）:52

广东省（粤）:44

青海省（青）:63

西藏（藏）: 54

四川省（川）:51

宁夏回族（宁）:64

海南省（琼）:46
	 */
	/**
	 * 邮编
	 * 北 京 市
地 区 邮政编码 地 区 邮政编码
北京市 100000 通州区 101100
平谷县 101200 顺义县 101300
怀柔县 101400 密云县 101500
延庆县 102100 昌平县 102200
门头沟区 102300 房山区 102400
大兴县 102600
上 海 市
地 区 邮政编码 地 区 邮政编码
上海市 200000 上海近郊区 200100
闵行区 201100 浦东新区 200120
南汇县 201300 奉贤县 201400
金山县 201500 松江县 201600
青浦县 201700 嘉定县 201800
宝山县 201900 崇明县 202150
天 津 市
地 区 邮政编码 地 区 邮政编码
天津市 300000 塘沽区 300450
汉沽区 300480 宁河县 301500
静海县 301600 武清县 301700
宝坻县 301800 蓟县 301900
重 庆 市
地 区 邮政编码 地 区 邮政编码
重庆市 400000 北碚区 400700
南桐矿区 400800 双桥区 400900
渝北区 401120 巴南区 401320
万县市 404000 涪陵市 408000
内 蒙 古
地 区 邮政编码 地 区 邮政编码
呼和浩特市 010000 集宁市 012000
二连浩特市 012600 包头市 014000
临河市 015000 乌海市 016000
东胜市 017000 海拉尔市 021000
满洲里市 021400 牙克石市 022150
赤峰市 024000 锡林浩特市 026000
通辽市 028000 霍林郭勒市 029200
乌兰浩特市 137400 扎兰屯市 162650
山 西 省
地 区 邮政编码 地 区 邮政编码
太原市 030000 古交市 030200
榆次市 030600 忻州市 034000
大同市 037000 朔州市 038500
临汾市 041000 侯马市 043000
运城市 044000 阳泉市 045000
长治市 046000 晋城市 048000
河 北 省
地 区 邮政编码 地 区 邮政编码
石家庄市 050000 南宫市 055750
辛集市 052360 衡水市 053000
邢台市 054000 沙河市 054100
邯郸市 056000 武安市 056300
沧州市 061000 泊头市 062150
任丘市 062550 唐山市 063000
廊坊市 065000 秦皇岛市 066000
北戴河 066100 承德市 067000
保定市 071000 涿州市 072750
定州市 073000 高碑店市 074000
张家口市 075000
辽 宁 省
地 区 邮政编码 地 区 邮政编码
沈阳市 110000 辽阳市 111000
铁岭市 112000 开原市 112300
铁法市 112700 抚顺市 113000
鞍山市 114000 海城市 114200
营口市 115000 大连市 116000
瓦房店市 116300 本溪市 117000
丹东市 118000 锦州市 121000
凌海市 121200 北宁市 121300
葫芦岛市 125000 兴城市 125100
朝阳市 122000 北票市 122100
阜新市 123000 盘锦市 124000
吉 林 省
地 区 邮政编码 地 区 邮政编码
长春市 130000 九台市 130500
扶余市 131200 大安市 131300
吉林市 132000 桦甸市 132400
延吉市 133000 图们市 133100
珲春市 133300 龙井市 133400
敦化市 133700 通化市 134000
集安市 134200 浑江市 134300
临江市 134600 梅河口市 135000
四平市 136000 公主岭市 136100
辽源市 136200 白城市 137000
洮南市 137100 松原市 138000
黑 龙 江 省
地 区 邮政编码 地 区 邮政编码
哈尔滨市 150000 双城市 150100
阿城市 150300 尚志市 150600
肇东市 151100 安达市 151400
绥化市 152000 铁力市 152500
伊春市 153000 佳木斯市 154000
鹤岗市 154100 七台河市 154600
双鸭山市 155100 富锦市 156100
同江市 156400 牡丹江市 157000
绥芬河市 157300 鸡西市 158100
密山市 158300 齐齐哈尔市 161000
大庆市 163000 北安市 164000
黑河市 164300 五大连池市 164100
江 苏 省
地 区 邮政编码 地 区 邮政编码
南京市 210000 仪征市 211400
镇江市 212000 丹阳市 212300
常州市 213000 无锡市 214000
宜兴市 214200 江阴市 214400
苏州市 215000 昆山市 215300
常熟市 215500 张家港市 215600
徐州市 221000 连云港市 222000
淮阴市 223000 淮安市 223200
宿迁市 223800 盐城市 224000
东台市 224200 扬州市 225000
泰州市 225300 兴化市 225700
南通市 226000
安 徽 省
地 区 邮政编码 地 区 邮政编码
合肥市 230000 淮南市 232000
蚌埠市 233000 宿州市 234000
淮北市 235000 阜阳市 236000
亳州市 236800 六安市 237000
巢湖市 238000 滁州市 239000
芜湖市 241000 宣州市 242000
黄山区 242700 马鞍山市 243000
铜陵市 244000 黄山市 245000
安庆市 246000 贵池市 247100
山 东 省
地 区 邮政编码 地 区 邮政编码
济南市 250000 聊城市 252000
临清市 252600 德州市 253000
乐陵市 253600 淄博市 255000
滨州市 256600 东营市 257000
潍坊市 261000 莱州市 261400
诸城市 262200 青州市 262500
烟台市 264000 威海市 264200
荣成市 264300 文登市 264400
莱阳市 265200 龙口市 265700
青岛市 266000 胶州市 266300
泰安市 271000 莱芜市 271100
新泰市 271200 济宁市 272100
曲阜市 273100 荷泽市 274000
临沂市 276000 日照市 276800
枣庄市 277100 滕州市 277500
浙 江 省
地 区 邮政编码 地 区 邮政编码
杭州市 310000 萧山市 311200
绍兴市 312000 湖州市 313000
嘉兴市 314000 宁波市 315000
慈溪市 315300 余姚市 315400
奉化市 315500 舟山市 316000
临海市 317000 椒江市 317700
台州市 318000 金华市 321000
兰溪市 321100 义乌市 322000
东阳市 322100 丽水市 323000
衢州市 324000 温州市 325000
瑞安市 325200
江 西 省
地 区 邮政编码 地 区 邮政编码
南昌市 330000 丰城市 331100
樟树市 331200 九江市 332000
景德镇市 333000 上饶市 334000
鹰潭市 335000 宜春市 336000
新余市 336500 萍乡市 337000
赣州市 341000 吉安市 343000
井冈山市 343600 抚州地区 344000
临川市 344100
福 建 省
地 区 邮政编码 地 区 邮政编码
福州市 350000 莆田市 351100
宁德市 352100 南平市 353000
邵武市 354000 厦门市 361000
泉州市 362000 石狮市 362700
漳州市 363000 龙岩市 364000
三明市 365000 永安市 366000
湖 南 省
地 区 邮政编码 地 区 邮政编码
长沙市 410000 湘潭市 411100
湘乡市 411400 株洲市 412000
醴陵市 412200 益阳市 413000
沅江市 413100 岳阳市 414000
汨罗市 414400 常德市 415000
津市市 415400 吉首市 416000
大庸市 416600 娄底市 417000
涟源市 417100 冷水江市 417500
怀化市 418000 洪江市 418200
衡阳市 421000 邵阳市 422000
郴州市 423000 资兴市 423400
永州市 425000 冷水滩市 425100
张家界市 427000
湖 北 省
地 区 邮政编码 地 区 邮政编码
武汉市 430000 麻城市 431600
天门市 431700 孝感市 432100
应城市 432400 安陆市 432600
广水市 432700 仙桃市 433000
潜江市 433100 洪湖市 433200
沙市市 434000 荆州市 434100
石首市 434400 荆门市 448000
黄石市 435000 鄂州市 436000
武穴市 436400 咸宁市 437000
蒲圻市 437300 襄樊市 441000
枣阳市 441200 随州市 441300
老河口市 441800 丹江口市 441900
十堰市 442000 宜昌市 443000
枝城市 443300 当阳市 444100
恩施市 445000 利川市 445400
河 南 省
地 区 邮政编码 地 区 邮政编码
郑州市 450000 禹州市 461670
新乡市 453000 卫辉市 453100
辉县市 453600 焦作市 454000
济源市 454650 安阳市 455000
林州市 456500 鹤壁市 458000
濮阳市 457000 许昌市 461000
漯河市 462000 驻马店地区 463000
信阳市 464000 周口地区 466000
平顶山市 467000 汝州市 467500
洛阳市 471000 三门峡市 472000
义马市 472300 南阳市 473000
邓州市 474150 开封市 475000
商丘市 476000
广 东 省
地 区 邮政编码 地 区 邮政编码
广州市 510000 清远市 511500
东莞市 511700 韶关市 512000
梅州市 514000 汕头市 515000
潮州市 521000 惠州市 516000
汕尾市 516600 河源市 517000
深圳市 518000 珠海市 519000
湛江市 524000 茂名市 525000
肇庆市 526000 佛山市 528000
中山市 528400 江门市 529000
海 南 省
地 区 邮政编码 地 区 邮政编码
海口市 570000 琼山市 571100
文昌市 571300 琼海市 571400
万宁市 571500 儋州市 571700
三亚市 572000 通什市 572200
西南中沙群岛 573100
广 西
地 区 邮政编码 地 区 邮政编码
南宁市 530000 凭祥市 532600
百色市 533000 钦州市 535000
北海市 536000 玉林市 537000
贵港市 537100 防城港市 538000
东兴市 538100 桂林市 541000
梧州市 543000 柳州市 545000
合山市 546500 河池市 547000
贵 州 省
地 区 邮政编码 地 区 邮政编码
贵阳市 550000 六盘水市 553000
铜仁市 554300 凯里市 556000
都匀市 558000 安顺市 561000
兴义市 562400 遵义市 563000
四 川 省
地 区 邮政编码 地 区 邮政编码
成都市 610000 都江堰市 611830
乐山市 614000 峨眉山市 614200
西昌市 615000 攀枝花市 617000
德阳市 618000 绵阳市 621000
雅安市 625000 广元市 628000
遂宁市 629000 达县市 635000
南充市 637000 华蓥市 638650
内江市 641000 自贡市 643000
宜宾市 644000 泸州市 646000
云 南 省
地 区 邮政编码 地 区 邮政编码
昆明市 650000 玉溪市 653100
东川市 654100 曲靖市 655000
照通市 657000 开远市 661000
个旧市 661400 大理市 671000
楚雄市 675000 保山市 678000
畹町市 678500
陕 西 省
地 区 邮政编码 地 区 邮政编码
西安市 710000 咸阳市 712000
渭南市 714000 韩城市 715400
延安市 716000 榆林市 719000
宝鸡市 721000 汉中市 723000
安康市 725000 商州市 726000
铜川市 727000
甘 肃 省
地 区 邮政编码 地 区 邮政编码
兰州市 730000 白银市 730900
临夏市 731100 武威市 733000
张掖市 734000 酒泉市 735000
嘉峪关市 735100 玉门市 735200
敦煌市 736200 金昌市 737100
天水市 741000 平凉市 744000
西峰市 745000
宁 夏
地 区 邮政编码 地 区 邮政编码
银川市 750000 吴忠市 751100
青铜峡市 751600 石咀山市 753000
青 海 省
地 区 邮政编码 地 区 邮政编码
西宁市 810000 格尔木市 816000
德令哈市 817000
新 疆
地 区 邮政编码 地 区 邮政编码
乌鲁木齐市 830000 昌吉市 831100
石河子市 832000 奎屯市 833200
博乐市 833400 克拉玛依市 834000
塔城市 834700 伊宁市 835000
阿勒泰市 836500 吐鲁番市 838000
哈密市 839000 库尔勒市 841000
阿克苏市 843000 喀什市 844000
阿图什市 845350 和田市 848000
西 藏
地 区 邮政编码 地 区 邮政编码
拉萨市 850000 日喀则市 857000
	 * @param args
	 */
	public static void main(String[] args) {
		InsureConfigVo insureConfigVo = InsureConfigVo.mockData();
		System.out.println(JSON.toJSONString(insureConfigVo));
		
	}
	
	
	

}
