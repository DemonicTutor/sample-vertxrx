package org.demonworld.sample.vertxrx;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.profile.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G",
  //"-verbose:gc",
  //"-XX:+PrintGCDateStamps",
  //"-XX:+PrintGCDetails",
  //"-Xloggc:./gc.log",
  //"-XX:+UseGCLogFileRotation",
  //"-XX:NumberOfGCLogFiles=10",
  //"-XX:GCLogFileSize=1M",
  //"-XX:+PrintGCApplicationStoppedTime",
  //"-XX:+PrintSafepointStatistics",
  //"-XX:PrintSafepointStatisticsCount=1",

  //"-Xlog:gc*,safepoint=trace:file=gc.log::filecount=1,filesize=1M",
  //"-Xlog:gc*,safepoint=trace",
  //"-XX:+PrintCompilation",
  "-XX:+UnlockDiagnosticVMOptions",
  //"-XX:+LogCompilation",
  //"-XX:+PrintInlining",
  "-Xcomp",
  "-Xbatch"},
  warmups = 1)
@State(Scope.Benchmark)
@Warmup(iterations = 1)
@Measurement(iterations = 1)
public class SampleBenchmark {

  public static void main(final String[] args) throws RunnerException {
    final Options opt = new OptionsBuilder()
      .include(SampleBenchmark.class.getSimpleName())
      .addProfiler(GCProfiler.class)
      .addProfiler(CompilerProfiler.class)
      .addProfiler(PausesProfiler.class)
      .addProfiler(StackProfiler.class)
      .addProfiler(ClassloaderProfiler.class)
      .addProfiler(HotspotMemoryProfiler.class)
      .addProfiler(HotspotClassloadingProfiler.class)
      .addProfiler(HotspotThreadProfiler.class)
      .addProfiler(HotspotRuntimeProfiler.class)
      .addProfiler(HotspotCompilationProfiler.class)
      .build();

    new Runner(opt).run();
  }

  @Param({"10000000"})
  private int N;
  private List<String> DATA_FOR_TESTING;

  @Setup
  public void setup() {
    DATA_FOR_TESTING = createData();
  }

  private List<String> createData() {
    final List<String> data = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      data.add("Number : " + i);
    }
    return data;
  }

  @Benchmark
  public void loopFor(final Blackhole bh) {
    for (int i = 0; i < DATA_FOR_TESTING.size(); i++) {
      final String s = DATA_FOR_TESTING.get(i); //take out n consume, fair with foreach
      bh.consume(s);
    }
  }

  @Benchmark
  public void loopWhile(final Blackhole bh) {
    int i = 0;
    while (i < DATA_FOR_TESTING.size()) {
      final String s = DATA_FOR_TESTING.get(i);
      bh.consume(s);
      i++;
    }
  }

  @Benchmark
  public void loopForEach(final Blackhole bh) {
    for (final String s : DATA_FOR_TESTING) {
      bh.consume(s);
    }
  }

  @Benchmark
  public void loopIterator(final Blackhole bh) {
    final Iterator<String> iterator = DATA_FOR_TESTING.iterator();
    while (iterator.hasNext()) {
      String s = iterator.next();
      bh.consume(s);
    }
  }
}
