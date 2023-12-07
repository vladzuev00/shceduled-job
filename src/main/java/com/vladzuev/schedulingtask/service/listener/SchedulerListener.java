package com.vladzuev.schedulingtask.service.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

@Slf4j
public final class SchedulerListener implements org.quartz.SchedulerListener {

    @Override
    public void jobScheduled(final Trigger trigger) {

    }

    @Override
    public void jobUnscheduled(final TriggerKey triggerKey) {

    }

    @Override
    public void triggerFinalized(final Trigger trigger) {

    }

    @Override
    public void triggerPaused(final TriggerKey triggerKey) {

    }

    @Override
    public void triggersPaused(final String triggerGroup) {

    }

    @Override
    public void triggerResumed(final TriggerKey triggerKey) {

    }

    @Override
    public void triggersResumed(final String triggerGroup) {

    }

    @Override
    public void jobAdded(final JobDetail jobDetail) {

    }

    @Override
    public void jobDeleted(final JobKey jobKey) {

    }

    @Override
    public void jobPaused(final JobKey jobKey) {

    }

    @Override
    public void jobsPaused(final String jobGroup) {

    }

    @Override
    public void jobResumed(final JobKey jobKey) {

    }

    @Override
    public void jobsResumed(final String jobGroup) {

    }

    @Override
    public void schedulerError(final String message, final SchedulerException cause) {

    }

    @Override
    public void schedulerInStandbyMode() {

    }

    @Override
    public void schedulerStarted() {

    }

    @Override
    public void schedulerStarting() {

    }

    @Override
    public void schedulerShutdown() {

    }

    @Override
    public void schedulerShuttingdown() {

    }

    @Override
    public void schedulingDataCleared() {

    }

}
