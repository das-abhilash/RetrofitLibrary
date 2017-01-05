package in.zollet.abhilash.retrofitlibrary.Model;

/**
 * Created by Abhilash on 12/27/2016.
 */

public class Result {


    enum Kyc {APPROVED,REJECTED,VALID}
     /*private enum Status {
        ONE("APPROVED"),
        TWO("APPROVED1"),
        THREE("APPROVED2"),
        FOUR("APPROVED3");
        private String kycStatus;

        Status(String  kycStatus) {
            this.kycStatus = kycStatus;
        }

        public String getValue() {
            return this.kycStatus;
        }
    }*/

    private String xyz  = "abc";
    // default value, primative type, enum, common error handling in rx java retrofit, multiple interface
    private Boolean onlineRentPaymentAvailable;
    private String role;
    private Kyc kycStatus;
    private String gender;
    private Integer mobileVerified;
    private String androidToken;
    private String secondaryContact;
    private Integer createdAt;
    private String zeloCode;
    private String referralCode;
    private String id;
    private String email;
    private String centerId;
    private Integer expectedDateOfVacancy;
    private String profilePic;
    private Integer active;
    private String roomName;
    private String centerLocalName;
    private String token;
    private Integer emailVerified;
    private Integer dateOfJoining;
    private String baseUrl;
    private String passwd;
    private Object noticeStartTime;
    private String primaryContact;
    private String tenantId;
    private String name;
    private Integer creditAmount;
    private String age;
    private String centerName;
    private String status;

    public Boolean getOnlineRentPaymentAvailable() {
        return onlineRentPaymentAvailable;
    }

    public void setOnlineRentPaymentAvailable(Boolean onlineRentPaymentAvailable) {
        this.onlineRentPaymentAvailable = onlineRentPaymentAvailable;
    }

    public String  getRole() {
        return role;
    }

    public void setRole(String  role) {
        this.role = role;
    }

    public Kyc getKycStatus() {
        return kycStatus;
    }

    public void setKycStatus(Kyc kycStatus) {
        this.kycStatus = kycStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(Integer mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    public String getAndroidToken() {
        return androidToken;
    }

    public void setAndroidToken(String androidToken) {
        this.androidToken = androidToken;
    }

    public String getSecondaryContact() {
        return secondaryContact;
    }

    public void setSecondaryContact(String secondaryContact) {
        this.secondaryContact = secondaryContact;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public String getZeloCode() {
        return zeloCode;
    }

    public void setZeloCode(String zeloCode) {
        this.zeloCode = zeloCode;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public Integer getExpectedDateOfVacancy() {
        return expectedDateOfVacancy;
    }

    public void setExpectedDateOfVacancy(Integer expectedDateOfVacancy) {
        this.expectedDateOfVacancy = expectedDateOfVacancy;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getCenterLocalName() {
        return centerLocalName;
    }

    public void setCenterLocalName(String centerLocalName) {
        this.centerLocalName = centerLocalName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Integer emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Integer getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Integer dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Object getNoticeStartTime() {
        return noticeStartTime;
    }

    public void setNoticeStartTime(Object noticeStartTime) {
        this.noticeStartTime = noticeStartTime;
    }

    public String getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(String primaryContact) {
        this.primaryContact = primaryContact;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Integer creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}