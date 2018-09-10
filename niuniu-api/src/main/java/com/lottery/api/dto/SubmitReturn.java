package com.lottery.api.dto;

import java.util.Map;

public class SubmitReturn {
		private boolean _success;	
		private String _error_message;
		private Map<String, String> _map_list;
		
		public boolean is_success() {
			return _success;
		}
		
		public void set_success(boolean _success) {
			this._success = _success;
		}

		public String get_error_message() {
			return _error_message;
		}

		public void set_error_message(String _error_message) {
			this._error_message = _error_message;
		}
		public Map<String, String> get_map_list() {
			return _map_list;
		}

		public void set_map_list(Map<String, String> _map_list) {
			this._map_list = _map_list;
		}
}
