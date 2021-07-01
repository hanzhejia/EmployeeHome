package com.csi.emphome.demo.utils.config;

import com.csi.emphome.demo.utils.other.EHConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
public class FileProperties {

    /** 文件大小限制 */
    private Long maxSize;

    /** 头像大小限制 */
    private Long avatarMaxSize;

    private ElPath mac;

    private ElPath linux;

    private ElPath windows;

    public ElPath getPath(){
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith(EHConstant.WIN)) {
            return windows;
        } else if(os.toLowerCase().startsWith(EHConstant.MAC)){
            return mac;
        }
        return linux;
    }

    public static class ElPath{

        private String path;

        private String avatar;

        public ElPath() {
        }

        public ElPath(String path, String avatar) {
            this.path = path;
            this.avatar = avatar;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        @Override
        public String toString() {
            return "ElPath{" +
                    "path='" + path + '\'' +
                    ", avatar='" + avatar + '\'' +
                    '}';
        }
    }

    public FileProperties() {
    }

    public FileProperties(Long maxSize, Long avatarMaxSize, ElPath mac, ElPath linux, ElPath windows) {
        this.maxSize = maxSize;
        this.avatarMaxSize = avatarMaxSize;
        this.mac = mac;
        this.linux = linux;
        this.windows = windows;
    }

    public Long getMaxSize() {
        maxSize = Long.parseLong("20");
        return maxSize;
    }

    public void setMaxSize(Long maxSize) {
        this.maxSize = maxSize;
    }

    public Long getAvatarMaxSize() {
        avatarMaxSize = Long.parseLong("20");
        return avatarMaxSize;
    }

    public void setAvatarMaxSize(Long avatarMaxSize) {
        this.avatarMaxSize = avatarMaxSize;
    }

    public ElPath getMac() {
        return mac;
    }

    public void setMac(ElPath mac) {
        this.mac = mac;
    }

    public ElPath getLinux() {
        return linux;
    }

    public void setLinux(ElPath linux) {
        this.linux = linux;
    }

    public ElPath getWindows() {
        return windows;
    }

    public void setWindows(ElPath windows) {
        this.windows = windows;
    }

    @Override
    public String toString() {
        return "FileProperties{" +
                "maxSize=" + maxSize +
                ", avatarMaxSize=" + avatarMaxSize +
                ", mac=" + mac +
                ", linux=" + linux +
                ", windows=" + windows +
                '}';
    }
}
