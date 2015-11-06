package tutorials.hibernate.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Created by guor on 2015/4/29.
 */
@Entity
@Table(name = "t_user")
public class User {

	public static final char ACTIVE = 'T';

	public static final char INACTIVE = 'F';

	@Id
	@GeneratedValue
	private long id;

	/**
	 * 用户名
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * 状态T活动，F禁用
	 */
	@Column(name = "active")
	private char active = ACTIVE;

	/**
	 * 创建时间
	 */
	@Column(name = "create_date")
	private Calendar createDate;

	/**
	 * 更新时间
	 */
	@Column(name = "update_date")
	@Version
	private Calendar updateDate;

	/**
	 * 昵称
	 */
	@Column(name = "display_name")
	private String displayName;

	/**
	 * 邮件地址
	 */
	@Column(name = "email_address")
	private String emailAddress;

	/**
	 * 密码，MD5加密
	 */
	@Column(name = "credential")
	private String credential;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the active
	 */
	public char getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(char active) {
		this.active = active;
	}

	/**
	 * @return the createDate
	 */
	public Calendar getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the updateDate
	 */
	public Calendar getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Calendar updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the credential
	 */
	public String getCredential() {
		return credential;
	}

	/**
	 * @param credential the credential to set
	 */
	public void setCredential(String credential) {
		this.credential = credential;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", active=" + active + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", displayName=" + displayName + ", emailAddress=" + emailAddress + ", credential=" + credential + "]";
	}
}
