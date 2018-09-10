package com.lottery.orm.result;

import com.lottery.orm.dto.LotteryServiceDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class InitInfoResult  extends BaseRestResult{
		
		@ApiModelProperty(value = "初始化信息", required = true)
		private LotteryServiceDto data;

		public void success(LotteryServiceDto data) {
			success();
			this.data = data;
		}

		public LotteryServiceDto getData() {
			return data;
		}

		public void setData(LotteryServiceDto data) {
			this.data = data;
		}
}
