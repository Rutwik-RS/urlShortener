package com.example.projecturl.urlshortener.dto;

public class dashboardResponse {
    private long totalUrls;
    private long activeUrls;
    private long expiredUrls;
    private long totalClicks;
    private String mostClickedUrl;
    private long mostClickCount;

    public dashboardResponse(long totalUrls, long activeUrls, long expiredUrls, long totalClicks, String mostClickedUrl, long mostClickCount) {
        this.totalUrls = totalUrls;
        this.activeUrls = activeUrls;
        this.expiredUrls = expiredUrls;
        this.totalClicks = totalClicks;
        this.mostClickedUrl = mostClickedUrl;
        this.mostClickCount = mostClickCount;
    }

    public dashboardResponse() {
    }

    public long getTotalUrls() {
        return totalUrls;
    }

    public void setTotalUrls(long totalUrls) {
        this.totalUrls = totalUrls;
    }

    public long getActiveUrls() {
        return activeUrls;
    }

    public void setActiveUrls(long activeUrls) {
        this.activeUrls = activeUrls;
    }

    public long getExpiredUrls() {
        return expiredUrls;
    }

    public void setExpiredUrls(long expiredUrls) {
        this.expiredUrls = expiredUrls;
    }

    public long gettotalClicks() {
        return totalClicks;
    }

    public void settotalClicks(long totalClicks) {
        this.totalClicks = totalClicks;
    }

    public String getMostClickedUrl() {
        return mostClickedUrl;
    }

    public void setMostClickedUrl(String mostClickedUrl) {
        this.mostClickedUrl = mostClickedUrl;
    }

    public long getMostClickCount() {
        return mostClickCount;
    }

    public void setMostClickCount(long mostClickCount) {
        this.mostClickCount = mostClickCount;
    }

    @Override
    public String toString() {
        return "dashboardResponse{" +
                "totalUrls=" + totalUrls +
                ", activeUrls=" + activeUrls +
                ", expiredUrls=" + expiredUrls +
                ", totalClicks=" + totalClicks +
                ", mostClickedUrl='" + mostClickedUrl + '\'' +
                ", mostClickCount=" + mostClickCount +
                '}';
    }
}