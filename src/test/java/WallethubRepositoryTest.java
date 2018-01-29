import entities.AccessLog;
import entities.BlockedAccess;
import entities.Scan;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.AccessLogService;
import service.BlockedAccessService;
import service.ScanService;

import java.util.Date;

public class WallethubRepositoryTest {
    private ScanService scanService;
    private AccessLogService accessLogService;
    private BlockedAccessService blockedAccessService;


    @Before
    public void before() {
        scanService = new ScanService();
        accessLogService = new AccessLogService();
        blockedAccessService = new BlockedAccessService();
    }
    @Test
    public void persistScan() {
        Scan scan = new Scan(null,new Date());
        scanService.save(scan);
        Scan scanned = scanService.findById(scan.getId());
        Assert.assertEquals(scan.getId(),scanned.getId());
    }
    @Test
    public void persistBlocked() {
        BlockedAccess blockedAccess = new BlockedAccess(null,"192.168.0.1","Blocked durint unitTest");
        blockedAccessService.save(blockedAccess);
        BlockedAccess savedblocked = blockedAccessService.findById(blockedAccess.getId());
        Assert.assertEquals(blockedAccess.getId(),savedblocked.getId());
    }
    @Test
    public void persistAccessLog() {
        AccessLog accessLog = new AccessLog(null, new Date(), "192.168.0.1","POST", 200,"IntellijIdea");
        accessLogService.save(accessLog);
        AccessLog savedAccesLog = accessLogService.findById(accessLog.getId());
        Assert.assertEquals(accessLog.getId(),savedAccesLog.getId());

    }




}