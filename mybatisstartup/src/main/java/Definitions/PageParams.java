package Definitions;

public class PageParams {
    // 当前页码
    private Integer pageNo;
    // 每页限制记录数
    private Integer pageSize;
    // 是否启用分页插件
    private Boolean useFlag;
    // 是否检查页码必须大于最大页数
    private Boolean checkPageNoLessThanSize;
    // 是否清除最后 order by 后面的语句
    private Boolean isCleanOrderBy;
    // 总记录数
    private Integer totalRecord;
    // 总页数
    private Integer totalPage;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean isUseFlag() {
        return useFlag;
    }

    public void setUseFlag(Boolean useFlag) {
        this.useFlag = useFlag;
    }

    public Boolean isCheckPageNoLessThanSize() {
        return checkPageNoLessThanSize;
    }

    public void setCheckPageNoLessThanSize(Boolean checkPageNoLessThanSize) {
        this.checkPageNoLessThanSize = checkPageNoLessThanSize;
    }

    public Boolean isCleanOrderBy() {
        return isCleanOrderBy;
    }

    public void setCleanOrderBy(Boolean cleanOrderBy) {
        isCleanOrderBy = cleanOrderBy;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
