package com.c2b.coin.matching.match.biz;


/**
 * 
 * 本接口用来封装撮合器需要调用、然而和业务相关的函数, 令其和撮合器逻辑分离
 * 
 * 包括不限于：计算订单优先级；计算成交金额等
 *
 * 优先级的设置方面：数字越小意味着越先被考虑
 * 	 优先级设置思路是：
 *     对于期权卖盘，同一价格下平仓优先可以视作把卖价略为调低
 *     对于期权买盘，同一价格下平仓优先可以视作把买价略为调高
 *     
 *     做法是引入调整因子 adjust. 普通的是0，需要增加优先级的，adjust > 0
 *     对于卖盘：
 *     		prior = (price << N - adjust)
 *     对于买盘：
 *          prior = -(price << N + adjust)
 *     
 *     这样，adjust 的合法区间就是 [0, 2^N -1] 了
 */

public interface BizAdaptor{
	//市价委托买入事件
	
	//市价委托卖出事件
	
	//限价委托买入事件
	
	//限价委托卖出事件
}
