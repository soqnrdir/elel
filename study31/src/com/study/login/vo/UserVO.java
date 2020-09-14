package com.study.login.vo;

//VO 생성시	Serializable 구현하는게 원칙입니다.
public class UserVO {
		private String userId;
		private String userName;
		private String userPass;
		private String userRole;

		// 기본생성자
		public UserVO() {
		}

		// 생성자
		public UserVO(String userId, String userName, String userPass, String userRole) {
			this.userId = userId;
			this.userName = userName;
			this.userPass = userPass;
			this.userRole = userRole;
		}
		// toString() 구현
		// 맴버필드의 get/set 메서드 생성

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getUserPass() {
			return userPass;
		}

		public void setUserPass(String userPass) {
			this.userPass = userPass;
		}

		public String getUserRole() {
			return userRole;
		}

		public void setUserRole(String userRole) {
			this.userRole = userRole;
		}

		@Override
		public String toString() {
			return "UserVO [userId=" + userId + ", userName=" + userName + ", userPass=" + userPass + ", userRole="
					+ userRole + "]";
		}
	}

