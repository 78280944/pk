package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.TradeInfoDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class TradeListResult extends BaseRestResult {
		
		@ApiModelProperty(value = "充值列表数据", required = true)
		private List<TradeInfoDto> data = null;

		public void success(List<TradeInfoDto> data) {
			success();
			this.data = data;
		}

		public List<TradeInfoDto> getData() {
			return data;
		}

		public void setData(List<TradeInfoDto> list) {
			this.data = list;
		}

	}
