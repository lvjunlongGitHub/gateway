package com.ljl.logging;

import lombok.Data;

/**
 * <P>
 *     用户访问日志
 * </P>
 * @author lvjunlong
 * @date 2019/8/22 下午7:11
 */
@Data
public class AccessLog {

    /**
     * 用户访问ip地址
     */
    private String ip;

    private String event;

    private String uri;
    /**
     * 请求端ua信息
     */
    private String ua;

    private String roleID; //	角色id

    private String roleName;//角色名称

    private String roleLevel;//	为空表示初始等级

    private String serverID;//	区服id

    private String serverName;

    private String referer;
    /**
     * 游戏ID
     */
    private String appId;

    private String channelId;

    private String osVersion;

    private String deviceId;

    private String uid;
    /**
     * 终端类型
     */
    private String deviceType;
    /**
     * 系统类型
     */
    private String deviceOS;

    private Integer timeStamp;
    /**
     * 扩展信息
     */
    private String ext;
    /**
     * 网络运营商
     */
    private String networkOperation;
    /**
     * 网络类型
     */
    private String networkType;
    /**
     * 版本号
     */
    private String versionNumber;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ip").append("=").append(ip).append("`");
        sb.append("event").append("=").append(event).append("`");
        sb.append("uri").append("=").append(uri).append("`");
        sb.append("ua").append("=").append(ua).append("`");
        sb.append("referer").append("=").append(referer).append("`");
        sb.append("appId").append("=").append(appId).append("`");
        sb.append("channelId").append("=").append(channelId).append("`");
        sb.append("userID").append("=").append(uid).append("`");
        sb.append("roleID").append("=").append(roleID).append("`");
        sb.append("roleName").append("=").append(roleName).append("`");
        sb.append("roleLevel").append("=").append(roleLevel).append("`");
        sb.append("serverID").append("=").append(serverID).append("`");
        sb.append("serverName").append("=").append(serverName).append("`");
        sb.append("deviceId").append("=").append(deviceId).append("`");
        sb.append("deviceType").append("=").append(deviceType).append("`");
        sb.append("deviceOS").append("=").append(deviceOS).append("`");
        sb.append("osVersion").append("=").append(osVersion).append("`");
        sb.append("timeStamp").append("=").append(timeStamp).append("`");
        sb.append("ext").append("=").append(ext).append("`");
        sb.append("networkOperation").append("=").append(networkOperation).append("`");
        sb.append("networkType").append("=").append(networkType).append("`");
        sb.append("versionNumber").append("=").append(versionNumber).append("`");
        return sb.toString();
    }
}
